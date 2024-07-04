package rs.ac.uns.ftn.informatika.jpa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import rs.ac.uns.ftn.informatika.jpa.dto.response.ResponseHostDTO;
import rs.ac.uns.ftn.informatika.jpa.model.Host;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHostDTO {

    @NotBlank(message = "Cant be empty")
    @Length(max = 50, message = "You have exceeded the allowed length (50)")
    private String name;

    @NotBlank(message = "Can't be empty")
    @Length(max = 50, message = "You have exceeded the allowed length (50)")
    private String surname;

    private String profilePicture;

    @NotBlank(message = "Cant't be empty")
    //@Pattern(regexp = "^(?=.*[0-9])(?!.*[^0-9])(.{5,50})$", message = "Must contain only numbers (MIN 5, MAX 50)")
    private String telephoneNumber;

    //@Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "{required}")
    @Length(max = 100, message = "Minimum 8, maximum 15 characters")
    private String address;

    @NotBlank(message = "Can't be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?!.*[^a-zA-Z0-9])(.{8,15})$", message = "Must contain 1 big letter (MIN 8, MAX 15)")
    private String password;

    public ResponseHostDTO parseToResponse(Long id){
        ResponseHostDTO driverResponse = new ResponseHostDTO(this.name, this.surname, this.profilePicture, this.telephoneNumber, this.email, this.address);
        driverResponse.setId(id);
        return driverResponse;
    }

    public Host parseToHost(){
        return new Host(this.name, this.surname, this.profilePicture, this.telephoneNumber, this.email, this.address, this.password);
    }
}
