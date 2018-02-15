package seedu.addressbook.ui;

import seedu.addressbook.commands.CommandResult;

import java.util.List;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

/**
 * Formatter for text to be displayed by Text UI
 */
public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    public String formatEnterCommand() {
        return LINE_PREFIX + "Enter command: ";
    }

    public String formatEnteredCommand(String fullInputLine) {
        return "[Command entered:" + fullInputLine + "]";
    }

    public String formatShowToUser(String message) {
        return LINE_PREFIX +  message.replace("\n", LS + LINE_PREFIX);
    }

    public String[] formatWelcomeMessage(String version, String storageFileFPath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFileFPath);
        return new String[] {
                            DIVIDER,
                            DIVIDER,
                            MESSAGE_WELCOME,
                            version,
                            MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                            storageFileInfo,
                            DIVIDER
                            };
    }

    public String[] formatGoodbyeMessage() {
        return new String[] { MESSAGE_GOODBYE, DIVIDER, DIVIDER };
    }

    public String[] formatInitFailedMessage() {
        return new String[] { MESSAGE_INIT_FAILED, DIVIDER, DIVIDER };
    }

    public String[] formatResultToUser(CommandResult result) {
        return new String[] {result.feedbackToUser, DIVIDER};
    }

    /** Formats a list of strings as a viewable indexed list. */
    public static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
