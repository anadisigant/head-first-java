# BeatBox - Cyber BeatBox

O Cyber BeatBox é uma máquina de bateria virtual simples baseada em Java que permite aos usuários criar e manipular padrões de bateria. O aplicativo fornece uma grade interativa de caixas de seleção, onde cada caixa corresponde a um som de instrumento específico, e os usuários podem ativá-las ou desativá-las para criar batidas personalizadas. O programa usa MIDI (Musical Instrument Digital Interface) para gerar os sons, permitindo ajuste de tempo e controle de reprodução.

## Funcionalidades
- **Grade de Instrumentos**: Uma grade 16x16 de caixas de seleção representando 16 instrumentos de bateria, cada um com 16 etapas para criação de batidas.
- **Controle de Tempo**: Ajuste do tempo (BPM) aumentando ou diminuindo a velocidade.
- **Controles de Reprodução**: Iniciar e parar a reprodução do padrão de bateria criado.
- **Limpar Padrão**: Limpar o padrão atual para criar uma nova composição.
- **Integração MIDI**: Usa o pacote `javax.sound.midi` do Java para produzir sons de kits de bateria pré-definidos.

## Instrumentos
Os seguintes sons de instrumentos estão disponíveis para seleção:

1. Bass Drum (Bombo)
2. Closed Hi-Hat (Chimbal Fechado)
3. Open Hi-Hat (Chimbal Aberto)
4. Acoustic Snare (Caixa Acústica)
5. Crash Cymbal (Prato Crash)
6. Hand Clap (Palmas)
7. High Tom (Tom Alto)
8. Hi Bongo (Bongo Alto)
9. Maracas
10. Whistle (Apito)
11. Low Conga (Conga Baixa)
12. Cowbell (Sino de Vaca)
13. Vibraslap
14. Low-mid Tom (Tom Baixo-médio)
15. High Agogo (Agogô Alto)
16. Open Hi Conga (Conga Alta Aberta)

Esses instrumentos são mapeados para números de notas MIDI específicos, que podem ser acionados selecionando as caixas de seleção correspondentes.

## Começando
Para rodar o aplicativo BeatBox, você precisará de:

- **Java Development Kit (JDK)**: Versão 8 ou superior.
- **Sistema compatível com MIDI**: Certifique-se de que seu sistema oferece suporte à reprodução de sons MIDI.

### Rodando o Aplicativo
1. Compile e execute o arquivo `BeatBox.java` usando seu IDE de Java preferido ou pela linha de comando.
2. A interface gráfica (GUI) aparecerá com uma grade de caixas de seleção, botões para controlar a reprodução e um painel para ajuste do tempo.
3. Clique nas caixas de seleção para criar um padrão de bateria e use os botões para iniciar, parar e controlar o tempo da sua composição.

## Uso
- **Start (Iniciar)**: Começa a reprodução do padrão de bateria.
- **Stop (Parar)**: Para a reprodução imediatamente.
- **Temp Up (Aumentar Tempo)**: Aumenta o tempo (BPM) em 3%.
- **Temp Down (Diminuir Tempo)**: Diminui o tempo (BPM) em 3%.
- **Clear (Limpar)**: Deselecta todas as caixas para limpar o padrão atual.

## Visão Geral do Código
A classe `BeatBox` cria a interface gráfica, inicializa o sequenciador MIDI e lida com as interações do usuário, como seleção de caixas de seleção e controle de reprodução. O sistema MIDI é usado para gerar sons e reproduzir a sequência em tempo real.

- **Componentes da GUI**:
  - `JPanel` para exibir a grade de caixas de seleção.
  - `JButton` para cada controle (Iniciar, Parar, Aumentar Tempo, Diminuir Tempo, Limpar).
  - `JCheckBox` para selecionar etapas individuais do padrão de batida.
  
- **Componentes MIDI**:
  - `Sequencer`: Controla a reprodução da sequência MIDI.
  - `Sequence`: Contém a sequência de eventos MIDI.
  - `Track`: Representa uma sequência de eventos musicais (notas, controles).
  
## Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para mais detalhes.

## Agradecimentos
- Pacote `javax.sound.midi` do Java para funcionalidade MIDI.
- A inspiração para o aplicativo vem de máquinas de bateria virtuais e softwares de criação de batidas.

---

Fique à vontade para modificar e expandir este projeto para uso pessoal ou educacional!
