package nsu.maxwell;

public class MessageFormatter {
    static String format(VersionInfo info) {
       return String.format(
               """
                       Здравствуйте, дорогая и.о. секретаря\s
                              
                       За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                       
                       Исчезли следующие страницы: %s
                       Появились следующие новые страницы: %s
                       Изменились следующие страницы: %s\s
                       
                       С уважением,
                       автоматизированная система
                       мониторинга.
                       """,
               info.vanished(), info.added(), info.edited());
    }
}
