using Microsoft.Owin.Hosting;
using Serilog;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer
{
    class Program
    {
        static void Main(string[] args)
        {
            // logging
            Log.Logger = new LoggerConfiguration()
                .CreateLogger();

            using (WebApp.Start<Startup>("http://localhost:12345"))
            {
                Console.WriteLine("server running...");
                Console.ReadLine();
            }
        }
    }
}
