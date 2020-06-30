package saetabis.automation.testingWrapper.services.api.responses.channelsService;

import lombok.Data;

@Data
public class Channels {

    private String summary;

    private String featured;

    private Images[] images;

    private Stitched stitched;

    private String plutoOfficeOnly;

    private String featuredOrder;

    private String number;

    private String isStitched;

    private String name;

    private String id;

    private String slug;

    private String hash;

    private String categoryID;

}
