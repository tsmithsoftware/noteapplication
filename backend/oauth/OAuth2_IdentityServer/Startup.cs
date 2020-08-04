﻿using IdentityServer3.Core.Configuration;
using IdentityServer3.Core.Services.InMemory;
using OAuth2_IdentityServer.Core.Models;
using Owin;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer
{
    class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            var options = new IdentityServerOptions
            {
                Factory = new IdentityServerServiceFactory()
                            .UseInMemoryClients(Clients.Get())
                            .UseInMemoryScopes(Scopes.Get())
                            .UseInMemoryUsers(new List<InMemoryUser>()),

                RequireSsl = false
            };

            app.UseIdentityServer(options);
        }
    }
}
