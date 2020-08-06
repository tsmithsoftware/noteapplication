using IdentityServer4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer.Models
{
    internal class Clients
    {
        public static IEnumerable<Client> Get() {
            return new List<Client>
            {
                new Client {
                    ClientId = "oAuthClient",
                    ClientName = "Notes client",
                    AllowedGrantTypes = GrantTypes.ClientCredentials,
                    ClientSecrets = new List<Secret> {
                        new Secret("15FCE3AC-C999-4735-99FD-AB6A04F67415".Sha256())
                    },
                    AllowedScopes = new List<string> {
                        "notesApi.all"
                    }
                }
            };
        }
    }
}
