import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class SolarSystem {
    private TransformGroup solarSystemTransformGroup;
    private Transform3D solarSystemTransform3D = new Transform3D();

    public static void main(String[] args) {
        new SolarSystem();
    }
    public SolarSystem() {
        BranchGroup scene = createGraphScene();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }
    public BranchGroup createGraphScene() {
        BranchGroup objRoot =new BranchGroup();
        solarSystemTransformGroup = new TransformGroup();
        solarSystemTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildSolarSystem();
        objRoot.addChild(solarSystemTransformGroup);

        // Configure lighting
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);

        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        return objRoot;
    }
    private void buildSolarSystem() {
        // create sun
        TransformGroup tgSun = new TransformGroup();
        Transform3D transformSun =new Transform3D();
        Sphere sun = Star.getSphere(0.5f, "source\\sunmap.jpg");
        Vector3f vectorSun = new Vector3f(0.0f, 0.0f, 0.0f);
        transformSun.setTranslation(vectorSun);
        tgSun.setTransform(transformSun);
        tgSun.addChild(sun);
        solarSystemTransformGroup.addChild(tgSun);

        // create Mercury
        
    }



}
