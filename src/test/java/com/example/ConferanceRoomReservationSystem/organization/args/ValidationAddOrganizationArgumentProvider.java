package com.example.ConferanceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class ValidationAddOrganizationArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                  "description": "IT company",
                                  "name": "I"
                                }
                                """,
                        Arrays.asList("size must be between 2 and 20")
                ),
                Arguments.of(
                        """
                                {
                                  "description": "IT company",
                                  "name": " "
                                }
                                """,
                        Arrays.asList("size must be between 2 and 20", "must not be blank")
                ),
                Arguments.of(
                        """
                                {
                                  "description": "IT company"
                                }
                                """,
                        Arrays.asList("must not be blank")
                )
        );
    }
}
