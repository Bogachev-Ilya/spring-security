# Authorisation Service

Service generate One-time password (4 digits) for existed User, and validate it on request.
1. Call POST localhost:8080/user/add , body: "{"username":"anyName",
   "password":"anyPassword"}". This step create a User in DB (in memory for this service)
2. Call POST localhost:8080/user/auth, with the same body on step 1 to generate one-time code.
3. Call POST localhost:8080/otp/check, body with username from step 1 and code from step 2 (it will be logged in console) : " {"username":"name from step 1",
   "code":"generate code from step 2"}". If code is correct server response 200 ok, otherwise 400 Bad request.

