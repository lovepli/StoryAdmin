package com.story.storyadmin.domain.entity.validationentity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author kdyzm
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ValidationResult {

    private Boolean hasErrors;

    private Map<String, String> errorMsg;

}
