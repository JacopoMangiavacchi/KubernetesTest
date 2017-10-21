﻿using System;
using System.IO;
using System.Text;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.AspNetCore.Routing;
using Newtonsoft.Json;

namespace csharptest
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var host = new WebHostBuilder()
                .UseKestrel()
                .UseStartup<Startup>()
                .UseUrls("http://localhost:8030")
                .Build();

            host.Run();
        }
    }

    public class Language
    {
        public string language { get; set; }
    }

    public class Request
    {
        public string url { get; set; }
    }

    public class Startup
    {
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddRouting();
        }

        public void Configure(IApplicationBuilder app)
        {
            var routeBuilder = new RouteBuilder(app);

            routeBuilder.MapGet("language", context => {
                Language language = new Language();
                language.language = "C#";

                string response = JsonConvert.SerializeObject(language);
                //string response = JsonConvert.SerializeObject(new { language = "C#" });

                return context.Response.WriteAsync(response);                
            });

            routeBuilder.MapPost("request", context => {
                //Get JSON Body
                string bodyAsString;
                using (var streamReader = new StreamReader(context.Request.Body, Encoding.UTF8))
                {
                    bodyAsString = streamReader.ReadToEnd();
                }

                Request request = JsonConvert.DeserializeObject<Request>(bodyAsString);

                //Call GET language url
                


                return context.Response.WriteAsync("C#");
            });

            var routes = routeBuilder.Build();
            app.UseRouter(routes);
        }
    }
}
