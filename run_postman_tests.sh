#!/bin/bash

ENV=$1
#Get output values, this is a somewhat naive approach since it is a lot of api calls

###
# todo- Eventually a user should be created dynamically for each run
###
#aws cognito-idp sign-up --client-id 6h8neevaj8ud8gal236671i1m6 --username first.last --password 1234ABcd! --user-attributes file://integration/auth/attributes.json


##login and grab tokens
authJson=`aws cognito-idp admin-initiate-auth --cli-input-json file://integration/auth/auth.json --query '{Token:AuthenticationResult.AccessToken,Id:AuthenticationResult.IdToken}'`

echo $authJson
##parse them out
id=`echo $authJson | jq -r '.Id'`
token=`echo $authJson | jq -r '.Token'`
echo $id
echo $token
newman run integration/Moodle_Accounts.postman_collection.json -e integration/moodle_accounts_${ENV}.postman_environment.json --timeout-request 15000