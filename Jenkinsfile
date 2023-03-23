pipeline{
    agent any
    stages {
        stage ("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage ("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }

        stage ("Code coverage") {
           steps {
                sh "./gradlew jacocoTestReport"
                publishHTML ( target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Report'
                ])
                sh "./gradlew jacocoTestCoverageVerification"
           }
        }

        stage ("Static code analysis") {
           steps {
                sh "./gradlew checkstyleMain"
                publishHTML ( target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: 'Checkstyle Report'
                ])
           }
        }

        stage ("Package") {
            steps {
                sh "./gradlew build"
            }
        }

        stage ("Docker build") {
            steps {
                sh "docker build -t ahmedhb81/calculator ."
            }
        }

        stage ("Docker push") {
            steps {
                withDockerRegistry([ credentialsId: "docker-registry-credentials", url: "https://index.docker.io/v1/" ]){
                    sh "docker push ahmedhb81/calculator"
                }
            }
        }

        stage ("Deploy to staging") {
            steps {
                sh "docker run --network=localnetwork -d --rm  -p  8765:8080 --name calculator ahmedhb81/calculator"
            }
        }

        stage ("Acceptance test") {
            steps {
                //sh "chmod +x Acceptance_test.bash && ./Acceptance_test.bash"
                sleep 60
                sh "./gradlew acceptanceTest -Dcalculator.url=http://10.0.0.3:8080"
            }
        }
    }

    post {
        always {
           sh "docker stop calculator"
        }
    }
}