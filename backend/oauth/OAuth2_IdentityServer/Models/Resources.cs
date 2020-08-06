using IdentityServer4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer.Models
{
    internal class Resources
    {
        public static IEnumerable<IdentityResource> GetIdentityResources()
        {
            return new[]
            {
            new IdentityResources.OpenId(),
            new IdentityResources.Profile(),
            new IdentityResources.Email(),
            new IdentityResource
            {
                Name = "role",
                UserClaims = new List<string> {"role"}
            }
        };
        }

        public static IEnumerable<ApiResource> GetApiResources()
        {
            return new[]
            {
            new ApiResource
            {
                Name = "notesApi",
                DisplayName = "Notes API",
                Description = "Allow the application to access Notes API on your behalf",
                Scopes = new List<string> { "notesApi.read", "notesApi.write", "notesApi.all"},
                ApiSecrets = new List<Secret> {new Secret("ScopeSecret".Sha256())},
                UserClaims = new List<string> {"role"}
            }
        };
        }

        public static IEnumerable<ApiScope> GetApiScopes()
        {
            return new[]
            {
            new ApiScope("notesApi.read", "Read Access to API #1"),
            new ApiScope("notesApi.write", "Write Access to API #1"),
            new ApiScope("notesApi.all", "Read/Write Access to API #1")
        };
        }
    }
}
