import java.awt.event.ActionListener;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class SolarSystem {
    private TransformGroup solarSystemTransformGroup;
    private Transform3D solarSystemTransform3D = new Transform3D();
    private BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100.0);

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
        Sphere sun = Star.getSphere(0.2f, "source\\sun.jpg");
        Vector3f vectorSun = new Vector3f(0.6f, 0.0f, 0.0f);
        PointLight sunLight = new PointLight(true,  new Color3f(1.0f, 1.0f, 1.0f),
                new Point3f(0.0f, 0.0f, 0.0f),
                new Point3f(1.0f, 0.0f, 0.0f));
        sunLight.setInfluencingBounds(bounds);
        transformSun.setTranslation(vectorSun);
        tgSun.setTransform(transformSun);
        tgSun.addChild(sun);
        tgSun.addChild(sunLight);
        solarSystemTransformGroup.addChild(tgSun);


        // create Mercury
        createPlanet(0.008f,0.23f,0.0f,0.0f, 10, "source\\mercury.jpg");
        // create Venus
        createPlanet(0.01f, 0.20f,0.0f,0.0f, 10, "source\\venus.jpg");
        // create Earth
        createPlanet(0.01f, 0.16f,0.0f,0.0f, 10, "source\\earth.jpg");
        // create Mars
        createPlanet(0.01f, 0.11f,0.0f,0.0f, 5, "source\\mars.jpg");
        // create Jupiter
        createPlanet(0.08f, -0.05f, 0.0f, 0.0f, 100,"source\\jupiter.jpg");
        //create Saturn
        createPlanet(0.05f, -0.2f, 0.0f, 0.0f, 1000, "source\\saturn.jpg");
        //create Uranus
        createPlanet(0.04f, -0.3f, 0.0f, 0.0f, 20, "source\\uranus.jpg");
        //create Neptune
        createPlanet(0.035f, -0.4f, 0.0f, 0.0f, 15, "source\\neptune.jpg");
        //create Pluto
        createPlanet(0.005f, -0.5f, 0.0f, 0.0f, 1000, "source\\pluto.jpg");

        MouseRotate rotate = new MouseRotate(solarSystemTransformGroup);
        rotate.setFactor (0.02);
        rotate.setSchedulingBounds (bounds);
        solarSystemTransformGroup.addChild(rotate);



    }
    private void createPlanet(float radius, float x, float y, float z, int selfRotationSpeed, String picture) {
        BoundingSphere bounds =new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        TransformGroup tgPlanet = new TransformGroup();
        Transform3D transformPlanet = new Transform3D();


        Sphere planet = Planet.getSphere(radius, picture);
        Vector3f vectorPlanet = new Vector3f(x, y, z);
        transformPlanet.setTranslation(vectorPlanet);
        tgPlanet.setTransform(transformPlanet);
        tgPlanet.addChild(planet);
        solarSystemTransformGroup.addChild(tgPlanet);
    }


}
