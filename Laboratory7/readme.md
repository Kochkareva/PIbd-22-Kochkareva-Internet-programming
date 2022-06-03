For prod environment:

1. Install PostgreSQL
2. createdb -U postgres sbapp\
   createdb -U postgres sbapp-test
3. gradlew bootRun -Pprod

For dev environment:\
gradlew bootRun
