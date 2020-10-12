1. install ASP.NET Core SDK 3.1 as described [here](https://docs.microsoft.com/en-us/dotnet/core/install/linux-debian#install-the-sdk)
1. clone repo
1. navigate to /netcore
1. use **generate-cert.sh** to generate a **server.pfx** certificate in the same folder - make sure to specify all passwords [link](https://razvangoga.blob.core.windows.net/share/http2-certificate-generation.png)
1. execute **dotnet run -c release {{cert_pass}}** - the webservice answers on 8080

