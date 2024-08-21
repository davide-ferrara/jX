### POST REQUEST TO ADD NEW USER 

curl -v --header "Content-Type: application/json" \
  --request POST \
  --data-binary @user.json \
  http://localhost:8080/api/v1/user/register

