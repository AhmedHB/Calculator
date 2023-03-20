pipeline{
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage("unit test") {
            sh "./gradlew test"
        }
    }
}