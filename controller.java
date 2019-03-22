import java.util.ArrayList;




import java.awt.event.ActionListener;
import java.util.ArrayList;

    ;
    public class controller {
        private player p;

        // initializing the reference of model class
        public void setModel(player p) {
            this.p = p;
        }

        // function to request the model to update the board
        public void setRequest(ArrayList<Integer> position) {
            p.PlayMove(position.get(0), position.get(1));
        }

        // overloaded function to request model to reset
        public void setRequest() {
            p.ResetModel();
        }

    }

