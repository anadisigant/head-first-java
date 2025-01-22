I. Elemento gráfico de desenho:

```java
   import java.awt.*;
   import javax.swing.*;

   class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(20, 50 ,100 ,100);
    }
   }
```

II. Figura JPEG:

```java
   public void paintComponent(Graphics g) {
    Image image = new ImageIcon("catzilla.jpg").getImage();

    g.drawImage(image, 3, 4, this);
   }
```

III. Círculo colorido aleatório em um plano de fundo negro:

```java
   public void paintComponent (Graphics g) {
    g.fillRect(0, 0, this.getWidth(), this.getHeight());

    int red = (int) (Math.random() * 255);
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);

    Color randomColor = new Color(red, green, blue);
    g.setColor(randomColor);
    g.fillOval(70, 70, 100, 100);
   }
```