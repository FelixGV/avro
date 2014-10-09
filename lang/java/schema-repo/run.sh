cd `dirname "$0"`                                 # connect to root
java -cp bundle/target/avro-repo-bundle-*-withdeps.jar org.apache.avro.repo.server.RepositoryServer bundle/config/config.properties
