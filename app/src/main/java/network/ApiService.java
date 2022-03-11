package network;

import models.BookResponse;

public interface ApiService {
    @GET("volumes")
    Call<BookResponse> getMyJSON(
            @Query("q") String query,
            @Query("maxResults") int maxResults);
}
