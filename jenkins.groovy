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
                    # Verify Terraform installation
                    terraform version
                '''
            }
        }

        stage('Setup') {
            steps {
                echo "Preparing Terraform Execution"
                    sh 'terraform init'
            }
        }

        stage('Plan') {
            steps {
                echo "Planning Infrastructure Changes"
                    sh 'terraform plan -out=tfplan'
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

