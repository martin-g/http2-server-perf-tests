using System.Net;
using System.Security.Cryptography.X509Certificates;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Server.Kestrel.Core;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.DependencyInjection;
using System;

namespace netcore_http2
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int port = 8080;
            Console.WriteLine($"Listening on {port}");
            CreateHostBuilder(args, port).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args, int port) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder
                    .ConfigureKestrel(options =>
                    {
                        options.Listen(IPAddress.Any, port, listenOptions =>
                        {
                            listenOptions.Protocols = HttpProtocols.Http2;
                            X509Certificate2 cert = new X509Certificate2("server.pfx", args[0]);
                            listenOptions.UseHttps(cert);
                        });
                    })
                    .Configure(app =>
                    {

                        app.UseHttpsRedirection();

                        app.UseRouting();

                        app.UseEndpoints(endpoints =>
                        {
                            endpoints.Map("/", httpContext =>
                            {
                                httpContext.Response.ContentType = "text/plain; charset=utf-8";
                                httpContext.Response.StatusCode = (int)HttpStatusCode.OK;

                                return httpContext.Response.WriteAsync("Hello world!");
                            });
                        });
                    });
                });               
    }
}
