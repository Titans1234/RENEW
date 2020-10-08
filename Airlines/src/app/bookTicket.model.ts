import { UserDetails } from "./userDetails.model";
import { PassengerDetails } from "./passengerDetails.model";
import { SeatBookDetails } from "./seatBookDetails.model";
import { TicketDetails } from "./ticketDetails.model";
export class BookTicket {

    customerDetails: UserDetails;
    passengerDetails: PassengerDetails[];
    seatDetails: SeatBookDetails[];
    ticketDetails: TicketDetails;


}
