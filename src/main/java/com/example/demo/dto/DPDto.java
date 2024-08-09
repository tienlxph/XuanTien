package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public class DPDto {
    private List<UUID> selectedIds;

    public List<UUID> getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(List<UUID> selectedIds) {
        this.selectedIds = selectedIds;
    }
}
