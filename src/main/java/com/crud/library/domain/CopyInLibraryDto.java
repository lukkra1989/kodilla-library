package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CopyInLibraryDto {

    private Long copyId;
    private Long bookId;
    private String status;

}
