import hudson.model.*;

def find_files(filetype){
    def files = findFiles(glob:filetype)
    for (file in files){
        println file.name
    }
}
def read_json_file(file_path){
    def propMap = readJSON file:file_path
    propMap.each{
        println(it.key + "=" + it.value)
    }
}
def read_json_text(json_string){
    def propMap = readJSON text:json_string
    propMap.each{
        println(it.key + "=" + it.value)
    }
}

def write_json_file(input_json, json_file){
    def input = ''
    if(input_json.toString().endWith(".json")){
        input = readJSON file: input_json
    }
    else{
        def json_Output = new JsonOutput()
        def new_json_object = jsonOutput.toJson(input_json)
        input = new_json_object
    }
    writeJSON file : json_file, json : input
}
return this;
