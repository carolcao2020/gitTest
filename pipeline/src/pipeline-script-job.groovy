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
                }
            }
        }
        stage('write json'){
            steps{
                script{
                    println "Write test_json.json to new_json.json"
                    json_file = env.WORKSPACE + "/testdata/test_json.json"
                    json_string = '{"Name": "carol","Age": 18,"City": "Beijing","Gender": "female"}'
                    new_json_file1 = env.WORKSPACE +"/testdata/new_json1.json"
                    new_json_file2 = env.WORKSPCAE + "/testdata/new_json2.json"
                    //model_test.write_json_file(json_file,new_json_file1)
                    //model_test.write_json_file(json_string,new_json_file2)
                    
                }
            }
        }
        stage('read properties file'){
            steps{
                script{
                    properties_file = env.WORKSPACE + "/testdata/test.properties"
                    println "Read property file"
                    model_test.read_properties(properties_file)
                }
            }
        }
        stage('Touch file'){
            steps{
                script{
                    touch_file = env.WORKSPACE + "/testdata/"+env.BUILD_NUMBER+".log"
                    touch touch_file
                }
            }
        }
        stage('Read yaml file'){
            steps{
                script{
                    yaml_file = env.WORKSPACE + "/testdata/test.yml"
                    model_test.read_yaml_file(yaml_file)
                    yaml_string = """
                    name: 'Lucy'
                    age: 18
                    city: 'Beijing'
                    ismale: false
                    """
                    model_test.read_yaml_file(yaml_string)
                }
            }
        }
        stage('write yaml file'){
            steps{
                script{
                    def amap=[name: 'Carol'
                             age: 18
                             city: 'Beijing'
                             ismale: false]
                    yaml_file_new = env.WORKSPACE + "/testdata/new.ylm"
                    model_test.write_to_yaml(yaml_file_new)
                    println "show the new yaml file content"
                    model_test.read_yaml_file(yaml_file_new)
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
