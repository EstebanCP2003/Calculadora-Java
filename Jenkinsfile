pipeline {
    agent any
    stages {
        stage('Clonar repositorio') {
            steps {
                git 'https://github.com/EstebanCP2003/Calculadora-Java.git'
            }
        }
        stage('Compilar') {
            steps {
                sh 'javac -d bin src/*.java'
            }
        }
        stage('Ejecutar pruebas') {
            steps {
                sh 'java -cp ".:lib/*:bin" org.junit.runner.JUnitCore NombreDeTuClaseTest'
            }
        }
    }
    post {
        always {
            junit 'test-reports/*.xml' // si generas reporte
        }
    }
}
