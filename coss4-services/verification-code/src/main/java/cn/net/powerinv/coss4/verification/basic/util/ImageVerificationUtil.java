package cn.net.powerinv.coss4.verification.basic.util;

import cn.net.powerinv.coss4.verification.basic.bean.ImageVerificationCode;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

@Component
public class ImageVerificationUtil {

    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
            'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static ImageVerificationCode createCode() throws IOException {
        return createCode(160, 40, 5, 100);
    }

    public static ImageVerificationCode createCode(int codeCount, int lineCount) throws IOException {
        return createCode(160, 40, codeCount, lineCount);
    }

    /**
     * 根据指定的图片宽高、字符数和干扰线个数生成图像验证码
     * @param width 图片宽度
     * @param height 图片高度
     * @param codeCount 验证码的字符个数
     * @param lineCount 验证码的干扰线数
     * @return {@link ImageVerificationCode} 图片和对应的验证码（区分大小写）
     * @throws IOException IOException
     */
    public static ImageVerificationCode createCode(int width, int height, int codeCount, int lineCount)
            throws IOException {
        int x = width / (codeCount + 2);    //每个字符的宽度(左右各空出一个字符)
        int fontHeight = height - 2;        //字体的高度
        int codeY = height - 4;

        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
        g.setFont(font);

        int red, green, blue;

        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            int xs = random.nextInt(width);                     // x坐标开始
            int ys = random.nextInt(height);                    // y坐标开始
            int xe = xs + random.nextInt(width / 8);    // x坐标结束
            int ye = ys + random.nextInt(height / 8);   // y坐标结束

            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起
            randomCode.append(strRand);
        }

        // 将图片转换成Base64编码
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String imageBase = "data:image/jpg;base64," + new String(Base64.encodeBase64(bytes));

        return new ImageVerificationCode(imageBase, randomCode.toString());
    }
}
