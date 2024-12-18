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
}

