/**
 * Diese Klasse stellt ein CD-foermiges Medium dar, dass 
 * BlueRay- und DVDDrive kompatibel ist und
 * nicht fest in einem Computer eingebaut ist
 * @author Christian
 *
 */
public abstract class BlueRayDVDLoadable extends Disc {

    /**
	 * Gibt das Medium in ein BlurRayDrive,
	 * vorrausgesetzt es befindet sich im moment das Medium nicht in einem Laufwerk
	 * und das Medium ist BlueRayKompatibel 
	 * @param type BlurRayDrive in das das Medium eingelegt wird
	 * @return true wenn erflogreich, wenn Fehler false
	 */
    public boolean loadingBR(BlueRayDrive type) {
        if (this.loadedby == null) {
            this.loadedby = type;
            return true;
        } else {
            return false;
        }
    }

    /**
	 * Gibt das Medium in ein DVD- oder USBDrive,
	 * vorrausgesetzt es befindet sich im moment das Medium nicht in einem Laufwerk
	 * und das Medium ist DVD- bzw. USBDrivekompatibel 
	 * @param type DVD- oder USBDrive in das das Medium eingelegt wird
	 * @return true wenn erflogreich, wenn Fehler false
	 */
    public boolean loadingDVD(DVDDrive type) {
        if (this.loadedby == null) {
            this.loadedby = type;
            return true;
        } else {
            return false;
        }
    }
}
