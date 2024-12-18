pipeline {
    agent any

    stages {
        stage('List Files') {
            steps {
                script {
                    sh 'ls -ls'
                }
            }
        }

        // stage('change directory') {
        //    steps {
        //          sh '''
        //             cd terraform
        //             pwd    
        //             # Download Terraform binary
        //             aws s3 cp s3://hash2buket/terraform . 

        //             ls -la
                        
        //             # Make the Terraform binary executable
        //             chmod +x terraform  
        //         '''
        //     }
        // }

        stage('check terrafrom version') {
            steps {
                 sh '''
                    pwd  
                    ls -la
                    # Verify Terraform installation
                    terraform version
                '''
            }
        }

        // stage('Install Terraform') {
        //     steps {
        //         script {
        //             echo "Installing Terraform"
        //             sh '''
                        // # Create a directory for Terraform binary
                        // mkdir -p ${TERRAFORM_DIR}
                        
                        // # Download Terraform binary
                        // aws s3 cp s3://hash2buket/terraform/ {TERRAFORM_DIR}
                        
                        // # Make the Terraform binary executable
                        // chmod +x ${TERRAFORM_DIR}/terraform
                        
                        // # Verify Terraform installation
                        // ${TERRAFORM_DIR}/terraform version
        //             '''
        //         }
        //     }
        // }

    //     stage('Setup') {
    //         steps {
    //             echo "Preparing Terraform Execution"
    //             // Initialize Terraform
    //             dir(TERRAFORM_DIR) {
    //                 sh 'terraform init'
    //             }
    //         }
    //     }

    //     stage('Plan') {
    //         steps {
    //             echo "Planning Infrastructure Changes"
    //             // Run Terraform plan
    //             dir(TERRAFORM_DIR) {
    //                 sh 'terraform plan -out=tfplan'
    //             }
    //         }
    //     }

    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
    }
}

