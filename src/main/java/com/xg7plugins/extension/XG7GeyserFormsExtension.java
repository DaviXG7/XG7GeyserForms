package com.xg7plugins.extension;

import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.forms.Form;
import com.xg7plugins.extensions.Extension;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.HashMap;

@Getter
public class XG7GeyserFormsExtension implements Extension {

    private final HashMap<String, Form<?,?>> forms = new HashMap<>();
    private static XG7GeyserFormsExtension instance;

    @Override
    public void onInit() {

        instance = this;

    }

    @Override
    public void onDisable() {

    }

    @Override
    public Plugin getPlugin() {
        return null;
    }

    @Override
    public String getName() {
        return "XG7GeyserForms";
    }

    public void registerForm(Form<?,?> creator) {
        if (creator == null) return;
        forms.put(creator.getId(), creator);
    }

    public void unregisterForm(String id) {
        forms.remove(id);
    }
    public boolean contaninsForm(String id) {
        return forms.containsKey(id);
    }

    public boolean sendForm(Player player, String form) {
        if (!FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) return false;
        if (!forms.containsKey(form)) return false;

        forms.get(form).send(player);

        return true;
    }
}