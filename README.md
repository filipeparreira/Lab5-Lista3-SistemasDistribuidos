# Lab5-Lista3-SistemasDistribuidos
## Alterações principais 
Os arquivos que hoveram alterações, ou foram criados, para possibilitar a conclusão do Laboratório são: *Peer.java*, *PeerLista.java*, *PeerWorkerEspec.java* e *InterfaceGraficaFrame.java*. Os demais arquivos presentes no projeto não foram modificados. Nestes arquivos modificados é que estão presentes o cabeçalho descrevendo as modificações realizadas.

Os arquivos **.java* estão localizandos nesta [pasta](https://github.com/filipeparreira/Lab5-Lista3-SistemasDistribuidos/tree/main/Lab5/src/main/java/com/lista3/lab5).

Mudou-se também a classe principal que executa o software. Inicialmente, a classe responsável pela execução (classe que contém o método main) era a classe *Peer.java*, porém, para conclusão dos itens requeridos na descrição do laboratório, viu-se a necessidade de que a classe principal para execução passasse a ser a classe *InterfaceGraficaFrame*, que é a responsável por realizar o controle dos peers.

Vale ressaltar que como não foi exigido na descrição do Laboratório a execução de funcionamento propriamente dita dos peers, o funcionamento do software não tem como enfoque tratar esta execução. Logo, o software tem como foco proporcionar um controle básico de peers, como a criação, a execlusão, e a criação selecionada dos mesmos. Entretanto, a execução dos peers continua acontecendo em terminal, porém sem um tratamento adequado para a execução individual deles.

## Execução
Para execução e teste do software é necessário utilizar a *versão 24 do Java SDK*, e por conta da interface gráfica é útil que se instale as dependências mais comuns necessárias para o Java Swing. Em sistemas operacionas baseados em Linux, para instalação das dependências basta executar os seguintes comandos: 
```bash
sudo apt-get update
sudo apt-get install -y libxtst6 libxi6 libxrender1
```  
Visando simplificar a execução do software como um todo, foi gerado o arquivo *.jar* para execução, sendo assim, basta clonar o repositório e executar o comando na pasta do repositório:  
```bash
java -jar Lab5-1.0-SNAPSHOT.jar
```  


