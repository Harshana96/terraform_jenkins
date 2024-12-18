pipeline {
    agent any

    environment {
        TERRAFORM_DIR = "./terraform" // Path to the Terraform files in the workspace
    }

    stages {
        stage('List Files') {
            steps {
                script {
                    sh 'ls -ls'
                }
            }
        }

        stage('Install Terraform') {
            steps {
                script {
                    echo "Installing Terraform"
                    sh '''
                        # Create a directory for Terraform binary
                        mkdir -p ${TERRAFORM_DIR}
                        
                        # Download Terraform binary
                        aws s3 cp s3://hash2buket/terraform_1.10.2_linux_amd64.zip .
                        
                        # Unzip the binary
                        unzip -o ${TERRAFORM_DIR}/terraform.zip -d ${TERRAFORM_DIR}
                        
                        # Make the Terraform binary executable
                        chmod +x ${TERRAFORM_DIR}/terraform
                        
                        # Verify Terraform installation
                        ${TERRAFORM_DIR}/terraform version
                    '''
                }
            }
        }

        stage('Setup') {
            steps {
                echo "Preparing Terraform Execution"
                // Initialize Terraform
                dir(TERRAFORM_DIR) {
                    sh 'terraform init'
                }
            }
        }

        stage('Plan') {
            steps {
                echo "Planning Infrastructure Changes"
                // Run Terraform plan
                dir(TERRAFORM_DIR) {
                    sh 'terraform plan -out=tfplan'
                }
            }
        }

    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
    }
}

