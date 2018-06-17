package mph.gui;

import util.CalendarUtility;
import mph.beans.dto.DeliverableDTO;

/**
 * Class used in the GUI, to sort a list of deliverables by deadline date.<br/>
 * It implements {@link Comparable}.
 */
public class ComparableDeliverable implements Comparable<ComparableDeliverable> {
		
		private DeliverableDTO dto;
		
		/**
		 * @param theDeliverable the object to store in the class
		 */
		public ComparableDeliverable(DeliverableDTO theDeliverable) {
			dto = theDeliverable;
		}
		
		/**
		 * @return the deliverable object
		 */
		public DeliverableDTO getDTO() {
			return dto;
		}
		
		@Override
		public int compareTo(ComparableDeliverable theOtherDeliverable) {
			if (CalendarUtility.after(this.dto.getDeadline(), theOtherDeliverable.dto.getDeadline())) {
				return -1;
			} else if (CalendarUtility.before(this.dto.getDeadline(), theOtherDeliverable.dto.getDeadline())) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
