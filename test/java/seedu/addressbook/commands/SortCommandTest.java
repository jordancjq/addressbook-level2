package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {

    private AddressBook sortedAddressBook;
    private AddressBook unsortedAddressBook;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), new UniqueTagList());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), new UniqueTagList());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                new UniqueTagList());

        sortedAddressBook = TestUtil.createAddressBook(davidGrant, janeDoe, johnDoe, samDoe);
        unsortedAddressBook = TestUtil.createAddressBook(johnDoe, davidGrant, samDoe, janeDoe);
    }

    @Test
    public void execute() throws IllegalValueException {
        assertSortingSuccessful(unsortedAddressBook, Collections.emptyList());
    }

    /**
     * Creates a new sort command.
     */
    private SortCommand createSortCommand(AddressBook addressBook, List<ReadOnlyPerson> displayList) {

        SortCommand command = new SortCommand();
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SortCommand sortCommand,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = sortCommand.execute();

        assertEquals(Command.getMessageForSortedPersonListShownSummary(expectedAddressBook.getAllPersons().immutableListView()), result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons().immutableListView(), actualAddressBook.getAllPersons().immutableListView());
    }

    /**
     * Asserts that the address book has been successfully sorted.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     * @throws PersonNotFoundException if the selected person is not in the address book
     */
    private void assertSortingSuccessful(AddressBook addressBook, List<ReadOnlyPerson> displayList) {

        AddressBook expectedAddressBook = TestUtil.clone(sortedAddressBook);

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        SortCommand command = createSortCommand(actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedAddressBook, actualAddressBook);
    }
}
