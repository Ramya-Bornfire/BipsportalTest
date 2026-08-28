import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FindWhiteBox {
    public static void main(String[] args) throws Exception {
        BufferedImage original = ImageIO.read(new File("src/main/resources/static/Image/QR_MUR_M.png"));
        int finalWidth = 900;
        int finalHeight = 1100;
        Image scaled = original.getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = resized.createGraphics();
        g.drawImage(scaled, 0, 0, null);
        g.dispose();
        
        int minX = finalWidth, minY = finalHeight, maxX = 0, maxY = 0;
        
        for (int y = 0; y < finalHeight; y++) {
            for (int x = 0; x < finalWidth; x++) {
                int rgb = resized.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g_ = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                
                // Pure white or very close
                if (r > 245 && g_ > 245 && b > 245) {
                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                    if (y < minY) minY = y;
                    if (y > maxY) maxY = y;
                }
            }
        }
        System.out.println("QR_MUR_M.png White Box: x=" + minX + ", y=" + minY + ", w=" + (maxX - minX) + ", h=" + (maxY - minY));
        System.out.println("Center X: " + (minX + (maxX - minX)/2) + ", Center Y: " + (minY + (maxY - minY)/2));

        // Also check NPCI_UPI_QR.png
        original = ImageIO.read(new File("src/main/resources/static/Image/NPCI_UPI_QR.png"));
        // The code draws this logoimage, and then draws the QR on it at 650, 1100 with w=1800, h=2200.
        // Let's just find the white box in the raw NPCI_UPI_QR.png
        minX = original.getWidth(); minY = original.getHeight(); maxX = 0; maxY = 0;
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                int rgb = original.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g_ = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                
                if (r > 245 && g_ > 245 && b > 245) {
                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                    if (y < minY) minY = y;
                    if (y > maxY) maxY = y;
                }
            }
        }
        System.out.println("NPCI_UPI_QR.png White Box: x=" + minX + ", y=" + minY + ", w=" + (maxX - minX) + ", h=" + (maxY - minY));
    }
}
