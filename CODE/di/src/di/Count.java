package di;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class Count {

    public Count(ImaginaryShip takeShip, ArrayList<Ship> otherShip) {
        for (int i = 0; i <= otherShip.size(); i++) {
            float targetShipX = otherShip.get(i).getLongitude() - takeShip.width;
            float targetShipY = otherShip.get(i).getLongitude() - takeShip.longitude;
            float targetShipVx = (float) (otherShip.get(i).getSpeed() * Math.cos(otherShip.get(i).getCourse()));
            float targetShipVy = (float) (otherShip.get(i).getSpeed() * Math.sin(otherShip.get(i).getCourse()));
            float targetShipD = 0;
            float targetShipR = otherShip.get(i).getCourse();

            float ownShipX = 0;
            float ownShipY = 0;
            float ownShipVx = 0;
            float ownShipVy = 0;
            float ownShipD = 0;
            float ownShipR = 150;

            float dangerSectorPoint1X = 0 + targetShipVx;
            float dangerSectorPoint1Y = 0 + targetShipVy;

            float x2 = targetShipX - ownShipX;
            float y2 = targetShipY - ownShipY;

            float r2 = (targetShipR + ownShipR) * (targetShipR + ownShipR);
            float R2 = x2 * x2 + y2 * y2;
            float theta = (x2 * x2 + y2 * y2 + R2 - r2) / 2;

            float ahelp = y2 * y2 + x2 * x2;
            float bhelp = - 2 * theta * y2;
            float chelp = theta * theta - x2 * x2 * R2;
            float dhelp = bhelp * bhelp - 4 * ahelp * chelp;
            float y3_1 = (float) ((-bhelp - sqrt(dhelp)) / (2 * ahelp));
            float y3_2 = (float) ((-bhelp + sqrt(dhelp)) / (2 * ahelp));
            float x3_1 = (theta - y2 * y3_1) / x2;
            float x3_2 = (theta - y2 * y3_2) / x2;

            float dangerSectorPoint2X = x3_1 + targetShipVx;
            float dangerSectorPoint2Y = y3_1 + targetShipVy;

            float dangerSectorPoint3X = x3_2 + targetShipVx;
            float dangerSectorPoint3Y = y3_2 + targetShipVy;
        }
    }
}
