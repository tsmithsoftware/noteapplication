using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using IdentityServer4.Configuration;
using IdentityServer4.Models;
using IdentityServer4.Test;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using OAuth2_IdentityServer.Models;

namespace OAuth2_IdentityServer
{
    public class Startup
    {
        private readonly IConfiguration Configuration;

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public static DirectoryInfo GetExecutingDirectory()
        {
            var location = new Uri(Assembly.GetEntryAssembly().GetName().CodeBase);
            return new FileInfo(location.AbsolutePath).Directory;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            Console.WriteLine("Does Certificates folder exist?");
            var dirExists = Directory.Exists("./Certificates");
            Console.WriteLine(dirExists);
            Console.WriteLine("How many files in Certificates?");
            DirectoryInfo directory = new DirectoryInfo("./Certificates");
            if (directory.Exists) {
                var files = directory.GetFiles();
                Console.WriteLine("Number of files in directory: " + files.Length);
                foreach (var file in files) {
                    Console.WriteLine(file.FullName);
                }
            }

            var signingCredential = GenerateSigningCredentials();
            services.AddIdentityServer()
                .AddInMemoryClients(Clients.Get())
                .AddInMemoryIdentityResources(Models.Resources.GetIdentityResources())
                .AddInMemoryApiResources(Models.Resources.GetApiResources())
                .AddInMemoryApiScopes(Models.Resources.GetApiScopes())
                .AddTestUsers(Users.Get())
                .AddSigningCredential(signingCredential)
                ;
        }

        private X509Certificate2 GenerateSigningCredentials()
        {
            string password = Configuration["Jwt:Secret"];
            Debug.Assert(!String.IsNullOrEmpty(password), "Jwt:Secret is missing from appsettings");
            string certificate = Configuration["Jwt:Certificate"];
            Debug.Assert(!String.IsNullOrEmpty(certificate), "Jwt:Certificate is missing from appsettings");
            if (!File.Exists(certificate)) throw new Exception("Certificate File does not exist");
            try
            {
                var cert = new X509Certificate2(
              certificate,
              password,
              X509KeyStorageFlags.MachineKeySet |
              X509KeyStorageFlags.PersistKeySet |
              X509KeyStorageFlags.Exportable
            );

                return cert;
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapGet("/", async context =>
                {
                    await context.Response.WriteAsync("Hello World!");
                });
            });

            app.UseIdentityServer();
        }
    }
}
