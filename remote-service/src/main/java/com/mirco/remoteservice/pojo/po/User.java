package com.mirco.remoteservice.pojo.po;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private long userId;
    @NonNull
    private String username;
    private String password;


}
