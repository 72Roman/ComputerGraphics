import java.awt.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Star {
    public static Sphere getSphere(float radius, String picture) {
        int primflags =Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Sphere(radius, primflags, getStarAppearence(picture));
    }
    private static Appearance getStarAppearence(String picture) {
        Appearance ap = new Appearance();
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f black = new Color3f(0f, 0f, 0f);
        ap.setMaterial(new Material(black, white, black, black, 1.0f));

        if(picture != "") {
            TextureLoader loader = new TextureLoader(picture, "LUMINANCE", new Container());
            Texture texture = loader.getTexture();

            texture.setBoundaryModeS(Texture.WRAP);
            texture.setBoundaryModeT(Texture.WRAP);
            texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

            TextureAttributes textAttr = new TextureAttributes();
            textAttr.setTextureMode(TextureAttributes.MODULATE);

            ap.setTexture(texture);
            ap.setTextureAttributes(textAttr);
        }
        return ap;
    }
}
