package com.example.netty.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.DefaultHttp2DataFrame;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.DefaultHttp2HeadersFrame;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2HeadersFrame;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

@Sharable
public class Http2ServerResponseHandler extends ChannelDuplexHandler {

    private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
    private static final String CONTENT_AS_STRING = "Hello world!";
    private static final byte[] CONTENT_AS_BYTES = CONTENT_AS_STRING.getBytes(StandardCharsets.UTF_8);
    private static final int CONTENT_LENGTH = CONTENT_AS_BYTES.length;
    static final ByteBuf RESPONSE_BYTES = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(CONTENT_AS_STRING, CharsetUtil.UTF_8));

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Http2HeadersFrame) {
            Http2HeadersFrame msgHeader = (Http2HeadersFrame) msg;
            if (msgHeader.isEndStream()) {
                ByteBuf content = ctx.alloc()
                    .buffer();
                content.writeBytes(RESPONSE_BYTES.duplicate());

                Http2Headers headers = new DefaultHttp2Headers().status(HttpResponseStatus.OK.codeAsText());
                headers.set("Content-Type", CONTENT_TYPE);
                headers.setInt("Content-Length", CONTENT_LENGTH);
                headers.set("Date", "Tue, 22 Dec 2020 08:03:41 GMT");
                ctx.write(new DefaultHttp2HeadersFrame(headers).stream(msgHeader.stream()));
                ctx.write(new DefaultHttp2DataFrame(content, true).stream(msgHeader.stream()));
            }

        } else {
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}
