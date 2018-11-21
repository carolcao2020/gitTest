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
                    json_file = env.WORKSPACE + "/testdata/test_json.json"
                    model_test.read_json_file(json_file)
                    println "##########################"
                    json_string = '{"Name":"carol","Age":18,"City":"Beijing","Gender":"female"}'
                    model_test.read_json_text(json_string)
                    println "Write test_json.json to new_json.json"
                    new_json_file1 = env.WORKSPACE +"/testdata/new_json1.json"
                    new_json_file2 = env.WORKSPCAE + "/testdata/new_json2.json"
                    write_json_file(json_file,new_json_file1)
                    write_json_file(json_string,new_json_file2)
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
