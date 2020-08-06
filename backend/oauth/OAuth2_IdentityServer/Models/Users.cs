using IdentityModel;
using IdentityServer4.Test;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace OAuth2_IdentityServer.Models
{
    internal class Users
    {
        public static List<TestUser> Get()
        {
            return new List<TestUser> {
                new TestUser {
                    SubjectId = "33843E2F-67EA-4C01-8C3B-FC99229AA1E8",
                    Username = "tsmith",
                    Password = "teh_Unit_P3ngu1n5!",
                    Claims = new List<Claim> {
                        new Claim(JwtClaimTypes.Email, "tijak@protonmail.ch"),
                        new Claim(JwtClaimTypes.Role, "admin")
                    }
                }
            };
        }
    }
}
