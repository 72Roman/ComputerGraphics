import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class SolarSystem implements ActionListener {
    private TransformGroup solarSystemTransformGroup;
    private Transform3D solarSystemTransform3D = new Transform3D();
    private Timer timer;

    public static void main(String[] args) {
        new SolarSystem();
    }
    public SolarSystem() {
        timer = new Timer(50, this);
        timer.start();
        BranchGroup scene = createGraphScene();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
    }

}
