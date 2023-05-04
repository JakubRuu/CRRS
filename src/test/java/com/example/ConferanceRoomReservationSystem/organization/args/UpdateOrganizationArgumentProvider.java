package com.example.ConferanceRoomReservationSystem.organization.args;

import com.example.ConferanceRoomReservationSystem.organization.Organization;
import com.example.ConferanceRoomReservationSystem.organization.OrganizationDTO;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UpdateOrganizationArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                //name
                //existing org
                //to update
                //expected
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDTO(null, "IT company"),
                        new Organization(null, "IT company"),
                        new Organization("Intive", "IT company"),
                        new OrganizationDTO("Intive", "IT company")
                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDTO("Tieto", null),
                        new Organization("Tieto", null),
                        new Organization("Tieto", "Delivery company"),
                        new OrganizationDTO("Tieto", "Delivery company")
                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDTO("Tieto", "IT company"),
                        new Organization("Tieto", "IT company"),
                        new Organization("Tieto", "IT company"),
                        new OrganizationDTO("Tieto", "IT company")
                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDTO(null, null),
                        new Organization(null, null),
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDTO("Intive", "Delivery company")
                )

        );
    }
}
