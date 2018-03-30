package io.github.abhimanbhau.wikinfofragment.utils;

/**
 * Created by akolte on 2/15/18.
 */

public class Constants {

    private static String[] _bigCats =
            {
                    "Cougar",
                    "Tiger",
                    "Cheetah",
                    "Leopard",
                    "Lion"
            };
    private static String[] _macOSVersions =
            {
                    "Mac_OS_X_Leopard",
                    "Mac_OS_X_Tiger",
                    "Mac_OS_X_Panther",
                    "OS_X_Mountain_Lion",
                    "OS_X_Mavericks"
            };
    private static String[] _comedyYoutubers =
            {
                    "H3h3Productions",
                    "Jack_Douglass",
                    "HowToBasic",
                    "Ryan_Higa",
                    "PewDiePie"
            };

    public static String[] get_bigCats() {
        return _bigCats;
    }

    public static String[] get_macOSVersions() {
        return _macOSVersions;
    }

    public static String[] get_comedyYoutubers() {
        return _comedyYoutubers;
    }
}
