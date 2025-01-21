# BeatBox

Este projeto tem como objetivo explorar o uso de som MIDI para criar uma máquina de ritmo em Java.

## Visão Geral do Projeto

O **BeatBox** é uma aplicação que permite ao usuário criar músicas utilizando batidas personalizadas. Ele faz uso das seguintes tecnologias e conceitos:
- **Java Swing** para criação da interface gráfica do usuário.
- **Java Sound API (MIDI)** para reproduzir sons musicais.
- Manipulação de eventos para responder às interações do usuário.

## Requisitos

- Java Development Kit (JDK) 8 ou superior.
- Um editor de texto ou IDE para compilar e executar o código (recomendado: IntelliJ IDEA, Eclipse, ou VS Code).

## Como Compilar e Executar

1. Clone este repositório:
   ```sh
   git clone https://github.com/anadisigant/head_first_java.git
   cd head_first_java/BeatBox
   ```

2. Navegue até o diretório do projeto:
   ```sh
   cd Capitulo11
   ```

3. Compile o código:
   ```sh
   javac *.java
   ```

4. Execute o programa:
   ```sh
   java BeatBox
   ```

## Descrição dos Arquivos Principais

- **BeatBox.java**: O arquivo principal que cria a interface gráfica e gerencia os eventos.
- **MiniMusicCmdLine.java**: Exemplo de execução de comandos MIDI a partir de argumentos de linha de comando.
- **MiniMiniMusicApp.java**: Um exemplo simples para reproduzir uma nota MIDI.

## Uso do MiniMusicCmdLine

O programa `MiniMusicCmdLine` permite tocar notas MIDI através da linha de comando:

### Exemplo de Uso:
```sh
java MiniMusicCmdLine <instrumento> <nota>
```
- **instrumento**: Um número inteiro representando o instrumento MIDI.
- **nota**: Um número inteiro representando a nota MIDI.

#### Exemplo:
```sh
java MiniMusicCmdLine 5 60
```
Este comando toca a nota 60 usando o instrumento 5.

## Problemas Comuns e Soluções

### `UnsupportedClassVersionError`
Se você encontrar um erro como:
```
Exception in thread "main" java.lang.UnsupportedClassVersionError
```
Verifique se está usando a mesma versão do Java para compilar e executar o código. Utilize:
```sh
java -version
javac -version
```
Atualize sua versão do Java se necessário ou compile o código com uma versão compatível:
```sh
javac --release 8 MiniMusicCmdLine.java
```

## Recursos

- [Head First Java (Livro)](https://www.oreilly.com/library/view/head-first-java/9781491910771/)
- [Documentação MIDI no Java](https://docs.oracle.com/javase/tutorial/sound/MIDI.html)

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

## Licença

Este projeto é fornecido sob a [Licença MIT](../LICENSE).

