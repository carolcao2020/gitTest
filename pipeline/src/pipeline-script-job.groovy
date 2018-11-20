import hudson.model.*;

println env.JOB_NAME
println env.BUILD_NUMBER
println env.WORKSPACE

pipeline{
    agent any
    stages{
        stage('init'){
            steps{
                script{
                    model_test = load env.WORKSPACE + "/pipeline/module/pipeline-demo-module.groovy"
                }
            }
        }
        stage('read json'){
            steps{
                script{
                    json_file = load env.WORKSPACE + "/testdata/test_json.json"
                    model_test.read_json_file(json_file)
                    println("##########################")
                    json_string = '{"Name":"carol","Age":18,"City":"Beijing","Gender":"female"}'
                    model_test.read_json_text(json_string)
                }
            }
        }
        stage('Test Method'){
            steps{
                script{
                    log_files = model_test.find_files('**/*.log')
                }
            }
        }
    }
}
