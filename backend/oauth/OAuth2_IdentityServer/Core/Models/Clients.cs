using IdentityServer3.Core.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer.Core.Models
{
    static class Clients
    {
        public static List<Client> Get()
        {
            return new List<Client>
        {
           // no human involved
            new Client
            {
                ClientName = "Silicon-only Client",
                ClientId = "silicon",
                Enabled = true,
                AccessTokenType = AccessTokenType.Reference,

                Flow = Flows.ClientCredentials,

                ClientSecrets = new List<Secret>
                {
                    new Secret("15FCE3AC-C999-4735-99FD-AB6A04F67415".Sha256())
                },

                AllowedScopes = new List<string>
                {
                    "notesApi"
                }
            }
        };
        }
    }
}
